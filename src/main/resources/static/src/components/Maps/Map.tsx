import * as React from "react";
import * as d3 from "d3";
import * as topojson from "topojson-client";
import { TransitionGroup, CSSTransition } from "react-transition-group";
import { IMapProps, IMapState } from "../../types/MapTypes";
import { State } from "./State";
import { MapService } from "../../services/MapService/MapService";

export class Map extends React.Component<IMapProps, IMapState> {

    constructor(props: any) {
        super(props);

        this.state = {
            maps: {}
        };
    }

    private generatePath(geoPath: any, data: any) {
        const { mapType } = this.props;

        return (
            <TransitionGroup component={null}>
                {data.map((feature: any, i: number) => {
                    // const breaks = this.getChoroplethBreaks();
                    const fill = "#F3F7F6";
                    const path = geoPath(feature);

                    return (
                        <CSSTransition
                            key={i}
                            classNames={`state-transition-${i}`}
                            appear={true}
                            timeout={5000}
                        >

                            <State
                                mapType={mapType}
                                stateName={feature.properties.stateName}
                                path={path}
                                feature={feature}
                                i={i}
                                fill={fill}
                                radius={20}
                            />

                        </CSSTransition>
                    );
                })}
            </TransitionGroup>
        );
    }

    public render(): JSX.Element {
        const { mapType } = this.props;
        const geoPath = d3.geoPath();
        const data = topojson.feature(this.state.maps.geoPath, this.state.maps.geoData.objects.states);
        const map = this.generatePath(geoPath, data);

        return (
            <div className="map-container">
                <svg
                    className={`map ${mapType}`}
                    xmlns="http://www.w3.org/2000/svg"
                    viewBox="0 0 960 600"
                >
                    {map}
                </svg>
            </div>
        );
    }

}