import * as React from "react";
import { sortedIndex, sortBy, uniq, round, concat, orderBy } from "lodash";
import * as d3 from "d3";
import * as topojson from "topojson";
import { TransitionGroup, CSSTransition } from "react-transition-group";

export class Map extends React.Component {

    constructor(props: any) {
        super(props);
    }

    private generatePath(geoPath, data) {
        const { mapType } = this.props;

        return (
            <TransitionGroup component={null}>
                {
                    data.map((feature, i) => {
                        const breaks = this.getChoroplethBreaks();
                        const fill = "#F3F7F6";

                        return (
                            <CSSTransition
                                key={i}
                                classNames={`state-transition-${i}`}
                                appear={true}
                                timeout={5000}
                            >

                                <State />

                            </CSSTransition>
                        )
                    });
                }
            </TransitionGroup>
        );
    }

}